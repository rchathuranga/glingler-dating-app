export class ChatDTO {
  constructor(
    public chatId?: number,
    public matchedId?: number,
    public sendProfileId?: number,
    public message?: string,
    public createdTime?: string){
  }
}
