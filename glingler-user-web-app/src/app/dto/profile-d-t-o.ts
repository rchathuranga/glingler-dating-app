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
    public ageRangeStart,
    public ageRangeEnd,
    public location,
    public lookingFor,
    public status,
    public matchCount,
  ) {
  }
}
