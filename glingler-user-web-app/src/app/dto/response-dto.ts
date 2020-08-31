export class ResponseDTO {
  constructor(public responseCode?: number,
              public responseError?: string,
              public data?: any[],
              public router?: string,
              public userId?: number,
              public token?: string) {
  }
}
