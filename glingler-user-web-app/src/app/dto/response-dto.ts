export class ResponseDTO {
  constructor(public responseCode?: number, public responseError?: string, public data?: any, public token?: string) {
  }
}
