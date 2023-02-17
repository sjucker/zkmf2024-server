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
    email: string;
    name: string;
    vorname: string;
    adresse: string;
    plzOrt: string;
    geburtsdatum: Date;
    telefon: string;
    vereinszugehoerigkeit: string;
    aufgaben: Aufgaben[];
    anzahlEinsaetze: string;
    einsatzMittwoch: Einsatzzeit[];
    einsatzDonnerstag: Einsatzzeit[];
    einsatzFreitag: Einsatzzeit[];
    einsatzSamstag: Einsatzzeit[];
    einsatzSonntag: Einsatzzeit[];
    einsatzMontag: Einsatzzeit[];
    einsatzDienstag: Einsatzzeit[];
    groesseShirt: string;
    comment: string;
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
    logoImgId?: number;
    bildImgId?: number;
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

export enum Aufgaben {
    EGAL = "EGAL",
    KOERPERLICH = "KOERPERLICH",
    MUSIKALISCH = "MUSIKALISCH",
    SERVICE = "SERVICE",
    BUFFET = "BUFFET",
    KUECHE = "KUECHE",
    NACHSCHUB = "NACHSCHUB",
    AUSSENSTAND = "AUSSENSTAND",
    RAHMENPROGRAMM = "RAHMENPROGRAMM",
    AUFBAU = "AUFBAU",
    BETREUUNG = "BETREUUNG",
    AUFSICHT = "AUFSICHT",
}

export enum Einsatzzeit {
    MORGEN = "MORGEN",
    MITTAG = "MITTAG",
    NACHMITTAG = "NACHMITTAG",
    ABEND = "ABEND",
    NACHT = "NACHT",
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
