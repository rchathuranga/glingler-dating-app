import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor() { }

  authenticate({username, password}): boolean{
    return (username === 'root' && password === 'ijse');
  }
}
