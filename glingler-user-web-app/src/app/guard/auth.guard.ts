import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticateService} from '../service/authenticate/authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(private authService: AuthenticateService,
              private router: Router) {
  }

  canActivate(): boolean {
    if (this.authService.isUserLogged()) {
      return true;
    } else {
      // this.router.navigate(['/login']);
      return true;
    }
  }

}
