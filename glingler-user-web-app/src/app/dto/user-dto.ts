export class UserDTO {
  constructor(public userId?: number,
              public username?: string,
              public email?: string,
              public status?: string,
              public userTypeId?: number,
              public userType?: string) {
  }
}
