import {Component} from '@angular/core';
import {DynamicDialogConfig} from "primeng/dynamicdialog";
import {environment} from "../../environments/environment";
import {Modul, TitelDTO, VereinDTO, VereinProgrammDTO} from "../rest";
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

    verein?: VereinDTO
    loading = true;

    constructor(config: DynamicDialogConfig<VereinDetailInput>,
                private vereineService: VereineService) {
        if (config.data) {
            this.loading = true;
            this.vereineService.get(config.data.id).subscribe({
                next: value => {
                    this.verein = value;
                    this.loading = false
                },
                error: _ => {
                    this.loading = false
                }
            });
        }
    }

    get logoImgSrc(): string {
        return `${environment.baseUrl}/public/image/${this.verein?.info.logoImgId}`;
    }

    get bildImgSrc(): string {
        return `${environment.baseUrl}/public/image/${this.verein?.info.bildImgId}`;
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
}
