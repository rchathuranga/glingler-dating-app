export class ProfileDTO {
  constructor(
    public profileId,
    public firstName,
    public lastName,
    public bio,
    public sex,
    public age,
    public birthday,
    public imageUrl,
    public createdTime,
    public userId,
    public status
  ) {
  }
}
