/* eslint-disable */

export interface ChangePasswordRequestDTO {
    oldPassword: string;
    newPassword: string;
}

export interface HelperRegistrationDTO {
    id: number;
    vorname: string;
    name: string;
    email: string;
    mobile: string;
    availableFriday: boolean;
    availableSaturday: boolean;
    availableSunday: boolean;
    comment?: string;
}

export interface KontaktDTO {
    vorname?: string;
    nachname?: string;
    adresse?: string;
    plz?: number;
    ort?: string;
    email?: string;
    telefonPrivat?: string;
    telefonMobile?: string;
}

export interface LoginRequestDTO {
    email: string;
    password: string;
}

export interface LoginResponseDTO {
    email: string;
    role: UserRole;
    jwt: string;
}

export interface NewsletterRecipientDTO {
    vorname: string;
    name: string;
    email: string;
    subscribedAt: Date;
    unsubscribedAt?: Date;
}

export interface RegisterHelperRequestDTO {
    vorname: string;
    name: string;
    email: string;
    mobile: string;
    comment: string;
    checkedDays: EventDays[];
}

export interface RegisterNewsletterRequestDTO {
    vorname: string;
    name: string;
    email: string;
}

export interface RegisterVereinRequestDTO {
    email: string;
    password: string;
    vereinsname: string;
}

export interface SurveyAnswerDTO {
    id?: number;
    vereinsName: string;
    besetzung: string[];
    staerkeKlasse: string;
    anzahlMitglieder: string;
    kontaktName: string;
    kontaktEmail: string;
    kontaktTelefon: string;
    modulAuswahl: string[];
    zusageKommentar?: string;
    absage: boolean;
    absageKommentar?: string;
    absageKontaktaufnahme?: string;
    helfer?: string;
}

export interface VereinDTO {
    email: string;
    angaben: VereinsangabenDTO;
    praesident: KontaktDTO;
    kassier: KontaktDTO;
    direktion: KontaktDTO;
    staerkeKlasse?: StaerkeKlasse;
    anzahlMusikanten?: number;
    anzahlDirigenten?: number;
    anzahlTambouren?: number;
}

export interface VereinsangabenDTO {
    vereinsname?: string;
    adresse?: string;
    plz?: number;
    ort?: string;
    homepage?: string;
    iban?: string;
}

export interface VerifyEmailRequestDTO {
    email: string;
    verification: string;
}

export enum UserRole {
    VEREIN = "VEREIN",
    HELPER = "HELPER",
    PLANER = "PLANER",
    ADMIN = "ADMIN",
}

export enum EventDays {
    FRIDAY = "FRIDAY",
    SATURDAY = "SATURDAY",
    SUNDAY = "SUNDAY",
}

export enum StaerkeKlasse {
    HOECHSTKLASSE = "HOECHSTKLASSE",
    KLASSE_1 = "KLASSE_1",
    KLASSE_2 = "KLASSE_2",
    KLASSE_3 = "KLASSE_3",
    KLASSE_4 = "KLASSE_4",
    UNTERSTUFE = "UNTERSTUFE",
    MITTELSTUFE = "MITTELSTUFE",
    OBERSTUFE = "OBERSTUFE",
}
