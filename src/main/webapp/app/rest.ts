/* eslint-disable */

export interface RegisterHelperRequestDTO {
    name: string;
    email: string;
    comment: string;
    checkedDays: EventDays[];
}

export interface RegisterNewsletterRequestDTO {
    name: string;
    email: string;
}

export interface RegisterRequestDTO {
    email: string;
    password: string;
    name: string;
    contactFirstName: string;
    contactLastName: string;
}

export enum EventDays {
    FRIDAY = "FRIDAY",
    SATURDAY = "SATURDAY",
    SUNDAY = "SUNDAY",
}
