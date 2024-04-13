export function read_data(): string;

export function read_data_for_save(): string;

export function set_data(data: string): void;

export function add_viewer(canvas_id: string, stage_width: number, location_id: string, data: string, print_view: boolean): string;

export function add_editor(canvas_id: string, stage_width: number, location_id: string, data: string): string;

export function set_debug_mode(): void;

export function is_data_dirty(): boolean;

export default function init(input: string): Promise<unknown>;
