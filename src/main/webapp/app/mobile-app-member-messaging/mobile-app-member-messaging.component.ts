import {Component, signal} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {MessageMemberDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {MessagingService} from "../service/messaging.service";

@Component({
    selector: 'app-mobile-app-member-messaging',
    templateUrl: './mobile-app-member-messaging.component.html',
    styleUrl: './mobile-app-member-messaging.component.sass'
})
export class MobileAppMemberMessagingComponent {
    dto: MessageMemberDTO = {
        identifier: '',
        title: '',
        body: '',
        route: '',
    };

    sending = signal(false);

    constructor(private readonly ref: DynamicDialogRef,
                private readonly service: MessagingService,
                private readonly messageService: MessageService,
                private readonly confirmationService: ConfirmationService,
                private authenticationService: AuthenticationService) {
    }

    send() {
        this.confirmationService.confirm({
            header: "Senden?",
            message: "Soll die Notification versandt werden?",
            acceptLabel: "Ja",
            rejectLabel: "Nein",
            accept: () => this.doSend(),
        });
    }

    private doSend() {
        this.sending.set(true);
        this.service.sendMemberNotification(this.dto).subscribe({
            next: () => {
                this.sending.set(false);
                this.dto = {
                    identifier: '',
                    title: '',
                    body: '',
                    route: '',
                };
                this.messageService.add({
                    severity: 'success',
                    summary: 'Gesendet',
                    detail: `Notification wurde versandt.`,
                    life: 5000,
                });
            },
            error: () => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: 'Ein Fehler ist aufgetreten',
                    life: 3000
                });
                this.sending.set(false);
            },
        });
    }

    close() {
        this.ref.close();
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }

    isValid() {
        return this.dto.identifier.length > 0
            && this.dto.title.length > 0
            && this.dto.body.length > 0
            && this.dto.route.length > 0;
    }

    get vereinIdentifiers(): string[] {
        return [
            'brass-band-eglisau',
            'brass-band-maur',
            'brass-band-musig-hittnau',
            'fusion-mallets',
            'harmonie-am-bachtel',
            'harmonie-eintracht-kuesnacht',
            'harmonie-eintracht-maennedorf',
            'harmonie-freienbach',
            'harmoniemusik-helvetia-horgen',
            'harmoniemusik-wald',
            'harmonie-pfaeffikon',
            'harmonie-wetzikon',
            'harmonie-zumikon',
            'jugendmusik-bezirk-affoltern',
            'kreismusik-limmattal',
            'kreismusik-limmattal-tambouren',
            'musikgesellschaft-andelfingen',
            'musikgesellschaft-faellanden',
            'musikgesellschaft-fehraltorf',
            'musikgesellschaft-glattfelden',
            'musikgesellschaft-haeggenschwil',
            'musikgesellschaft-harmonie-turbenthal',
            'musikgesellschaft-niederhasli',
            'musikgesellschaft-rafz',
            'musikgesellschaft-seuzach',
            'musikverein-baeretswil',
            'musikverein-bonstetten',
            'musikverein-brass-band-henggart',
            'musikverein-buchs-zh',
            'musikverein-dietlikon',
            'musikverein-eintracht-wallisellen',
            'musikverein-gossau-zh',
            'musikverein-grueningen',
            'musikverein-harmonie-adliswil',
            'musikverein-harmonie-affoltern-am-albis',
            'musikverein-harmonie-altstetten',
            'musikverein-harmonie-bauma',
            'musikverein-harmonie-birmensdorf',
            'musikverein-harmonie-hausen-am-albis',
            'musikverein-harmonie-thalwil',
            'musikverein-harmonie-waedenswil',
            'musikverein-harmonie-zuerich-oberstrass',
            'musikverein-harmonie-zuerich-schwamendingen',
            'musikverein-hedingen',
            'musikverein-helvetia-marthalen',
            'musikverein-kempttal',
            'musikverein-langnau-am-albis',
            'musikverein-meilen',
            'musikverein-neerach',
            'musikverein-neftenbach',
            'musikverein-oberrieden',
            'musikverein-richterswil-samstagern',
            'musikverein-ruemlang',
            'musikverein-schoenenberg',
            'musikverein-stammheim',
            'musikverein-uetikon-am-see',
            'musikverein-weisslingen',
            'musikverein-wil-zh',
            'musikverein-zuerich-hoengg',
            'musikverein-zuerich-seebach',
            'perkussionsensemble-winterthur',
            'schlagzeugensemble-florhof',
            'sjmuz-percussion-ensemble',
            'sjmuz-schlagzeugensemble',
            'spielgemeinschaft-mettmenstetten-ottenbach',
            'stadtharmonie-winterthur-toess',
            'stadtharmonie-zuerich-oerlikon-seebach',
            'stadtmusik-buelach',
            'stadtmusik-dietikon',
            'stadtmusik-duebendorf',
            'stadtmusik-illnau-effretikon',
            'stadtmusik-winterthur',
            'stadtmusik-zuerich',
            'tambouren-polizeimusik-zuerich-stadt',
            'tambourenverein-stadt-winterthur',
            'tambourenverein-weinland-andelfingen',
            'vbz-musik',
        ];
    }
}
