import {Component, OnInit, signal} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {delay} from "rxjs";
import {fromPromise} from "rxjs/internal/observable/innerFrom";
import init, {add_viewer} from "../../assets/stage/wasm_stage";

@Component({
    selector: 'app-stage-setups',
    templateUrl: './stage-setup.component.html',
    styleUrl: './stage-setup.component.sass'
})
export class StageSetupComponent implements OnInit {

    canvasId = 'stage-canvas';

    verein = ''

    ready = signal(false);

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        const locationIdentifier = this.route.snapshot.paramMap.get('locationid')!;
        const json = this.route.snapshot.paramMap.get('json')!;
        const wasm$ = fromPromise(init("/assets/stage/stager.wasm"));
        wasm$.pipe(
            // wait some time, so canvas is available for sure
            delay(200)
        ).subscribe(() => {
            add_viewer(this.canvasId, 900, locationIdentifier, json, true);
            setTimeout(() => {
                this.ready.set(true);
            }, 200);
        });
    }
}
