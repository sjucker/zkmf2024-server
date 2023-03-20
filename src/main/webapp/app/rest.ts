/* eslint-disable */

export interface ForgotPasswordRequestDTO {
    email: string;
}

export interface KontaktDTO extends IsValid {
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
    subscribedAt: DateAsString;
    unsubscribedAt?: DateAsString;
}

export interface RegisterHelperRequestDTO {
    email: string;
    name: string;
    vorname: string;
    adresse: string;
    plzOrt: string;
    geburtsdatum: DateAsString;
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

export interface ResetPasswordRequestDTO {
    email: string;
    token: string;
    newPassword: string;
}

export interface VereinDTO {
    email: string;
    angaben: VereinsangabenDTO;
    praesident: KontaktDTO;
    direktion: KontaktDTO;
    anmeldung: VereinsanmeldungDTO;
    info: VereinsinfoDTO;
}

export interface VereinsangabenDTO extends IsValid {
    vereinsname?: string;
    adresse?: string;
    plz?: number;
    ort?: string;
    homepage?: string;
    iban?: string;
}

export interface VereinsanmeldungDTO extends IsValid {
    modulA: boolean;
    modulB: boolean;
    modulC: boolean;
    modulD: boolean;
    modulE: boolean;
    modulF: boolean;
    modulG: boolean;
    modulH: boolean;
    klasseModulA?: Klasse;
    klasseModulB?: Klasse;
    klasseModulH?: Klasse;
    harmonie: boolean;
    brassBand: boolean;
    fanfare: boolean;
    tambouren: boolean;
    perkussionsensemble: boolean;
}

export interface VereinsinfoDTO extends IsValid {
    logoImgId?: number;
    bildImgId?: number;
}

export interface VerifyEmailRequestDTO {
    email: string;
    verification: string;
}

export interface IsValid {
    valid: boolean;
}

export type DateAsString = string;

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

export enum Klasse {
    HOECHSTKLASSE = "HOECHSTKLASSE",
    KLASSE_1 = "KLASSE_1",
    KLASSE_2 = "KLASSE_2",
    KLASSE_3 = "KLASSE_3",
    KLASSE_4 = "KLASSE_4",
    OBERSTUFE = "OBERSTUFE",
    MITTELSTUFE = "MITTELSTUFE",
    UNTERSTUFE = "UNTERSTUFE",
}
