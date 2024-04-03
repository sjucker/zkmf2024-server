import {Component} from '@angular/core';
import {DynamicDialogConfig} from "primeng/dynamicdialog";
import {combineLatestWith, delay} from "rxjs";
import {fromPromise} from "rxjs/internal/observable/innerFrom";
import init, {add_viewer} from "../../assets/stage/wasm_stage";
import {environment} from "../../environments/environment";
import {Modul, PhaseStatus, TitelDTO, VereinDTO, VereinProgrammDTO, VereinStageSetupDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {VereineService} from "../service/vereine.service";

export interface VereinDetailInput {
    id: number
}

@Component({
    selector: 'app-verein-detail',
    templateUrl: './verein-detail.component.html',
    styleUrls: ['./verein-detail.component.sass']
})
export class VereinDetailComponent {

    verein?: VereinDTO;
    vereinId?: number;
    loading = true;
    confirming = false;

    canvasId = 'stage-canvas';
    stageSetup: VereinStageSetupDTO = {stageSetup: "{}", dirigentenpodest: false, locationIdentifier: ""};

    constructor(config: DynamicDialogConfig<VereinDetailInput>,
                private authenticationService: AuthenticationService,
                private vereineService: VereineService) {
        if (config.data) {
            this.loading = true;
            this.vereinId = config.data.id;
            this.vereineService.get(config.data.id).subscribe({
                next: value => {
                    this.verein = value;
                    this.loading = false
                    if (this.verein.anmeldung.modulA ||
                        this.verein.anmeldung.modulB ||
                        this.verein.anmeldung.modulH) {
                        this.loadStageSetup(this.vereinId!);
                    }
                },
                error: () => {
                    this.loading = false
                }
            });
        }
    }

    private loadStageSetup(id: number) {
        const wasm$ = fromPromise(init("/assets/stage/stager.wasm"));
        const stage$ = this.vereineService.getStageSetup(id);
        wasm$.pipe(
            combineLatestWith(stage$),
            // wait some time, so canvas is available for sure
            delay(500)
        ).subscribe((data) => {
            this.stageSetup = data[1];
            add_viewer(this.canvasId, 900, this.stageSetup.locationIdentifier, this.stageSetup.stageSetup)
        });
    }

    get logoImgSrc(): string {
        return `${environment.imageDeliveryUrl}${this.verein?.info.logoImgCloudflareId}/public`;
    }

    get bildImgSrc(): string {
        return `${environment.imageDeliveryUrl}${this.verein?.info.bildImgCloudflareId}/public`;
    }

    isModulAB(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.A || programm.modul === Modul.B;
    }

    isModulB(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.B;
    }

    isModulC(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.C;
    }

    isModulD(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.D;
    }

    isModulDEF(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.D || programm.modul === Modul.E || programm.modul === Modul.F;
    }

    isModulG(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.G;
    }

    isModulH(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.H;
    }

    isModulGH(programm: VereinProgrammDTO): boolean {
        return programm.modul === Modul.G || programm.modul === Modul.H;
    }

    hasVorgabeDauer(programm: VereinProgrammDTO): boolean {
        return !!(programm.minDurationInSeconds && programm.maxDurationInSeconds);
    }

    isInRange(programm: VereinProgrammDTO): boolean {
        if (programm.totalDurationInSeconds && programm.minDurationInSeconds && programm.maxDurationInSeconds) {
            return programm.totalDurationInSeconds >= programm.minDurationInSeconds && programm.totalDurationInSeconds <= programm.maxDurationInSeconds;
        }
        return true;
    }

    formatDuration(durationInSeconds?: number): string {
        if (durationInSeconds) {
            const seconds = Math.abs(durationInSeconds);
            let formatted = `${Math.floor(seconds / 60)}:${String(seconds % 60).padStart(2, '0')}`;

            if (durationInSeconds < 0) {
                formatted = "-" + formatted;
            }

            return formatted;
        }

        return '';
    }

    selbstwahlTitel(programm: VereinProgrammDTO): TitelDTO[] {
        return programm.ablauf.map(e => e.titel).filter(t => !t.pflichtStueck);
    }

    isBlank(titel: string | undefined): boolean {
        if (titel) {
            return titel.trim().length === 0;
        }
        return true;
    }

    confirm() {
        if (this.vereinId) {
            this.confirming = true;
            this.vereineService.confirmProgramm(this.vereinId).subscribe({
                next: value => {
                    this.confirming = false;
                    this.verein = value;
                },
                error: err => {
                    this.confirming = false;
                    console.error(err);
                }
            });
        }
    }

    get phase2Done(): boolean {
        if (this.verein) {
            return this.verein.phase2Status === PhaseStatus.DONE;
        }
        return false;
    }

    get phase2Confirmed(): boolean {
        if (this.verein) {
            return !!this.verein.phase2ConfirmedAt;
        }
        return false;
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }

}
