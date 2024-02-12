import {Component} from '@angular/core';
import {DynamicDialogConfig} from "primeng/dynamicdialog";
import {VereinCommentDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {VereineService} from "../service/vereine.service";

export interface VereinCommentsInput {
    id: number
}

@Component({
    selector: 'app-verein-comments',
    templateUrl: './verein-comments.component.html',
    styleUrls: ['./verein-comments.component.sass']
})
export class VereinCommentsComponent {
    comments: VereinCommentDTO[] = [];
    loading = true;
    saving = false;

    comment = '';

    constructor(private readonly config: DynamicDialogConfig<VereinCommentsInput>,
                private authenticationService: AuthenticationService,
                private vereineService: VereineService) {
        if (config.data) {
            this.loading = true;
            this.vereineService.getComments(config.data.id).subscribe({
                next: value => {
                    this.comments = value;
                    this.loading = false
                },
                error: err => {
                    console.error(err);
                    this.loading = false
                }
            });
        }
    }

    saveComment() {
        if (this.config.data) {
            this.saving = true;
            this.vereineService.saveComment(this.config.data.id, this.comment).subscribe({
                next: value => {
                    this.comments = [value, ...this.comments];
                    this.comment = '';
                    this.saving = false;
                },
                error: err => {
                    console.error(err);
                    this.saving = false;
                }
            });
        }
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
