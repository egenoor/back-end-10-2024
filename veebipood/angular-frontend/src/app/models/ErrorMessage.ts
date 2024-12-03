import { HttpHeaders } from '@angular/common/http'

export class ErrorResponse {
  constructor(
    public error: ErrorMessage,
    public ok: boolean,
    public status: number,
    public statusText: string,
    public url: string,
    public message: string,
    public name: string,
    public headers: HttpHeaders
  ) {}
}

export class ErrorMessage {
  constructor(
    public name: string,
    public date: Date
  ) {}
}