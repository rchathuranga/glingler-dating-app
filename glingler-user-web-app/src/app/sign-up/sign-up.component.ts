import {Component, OnInit} from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import {AngularFireStorage, AngularFireStorageReference, AngularFireUploadTask} from '@angular/fire/storage';
import {Router} from '@angular/router';
import {ProfileService} from '../service/profile/profile.service';
import {finalize} from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  ref: AngularFireStorageReference;
  task: AngularFireUploadTask;

  imageUrl: string | ArrayBuffer = 'https://classeventie.classiebit.com//upload/users/images/1537003256339.png';
  imageFile: File;

  passwordHide = true;
  profileImgHide = true;
  minDate: Date = new Date(1990, 0, 1);

  constructor(private formBuilder: FormBuilder,
              private afStorage: AngularFireStorage,
              private router: Router,
              private profileService: ProfileService) {
  }

  ngOnInit(): void {
  }

  async btnCreateAccount(form: NgForm) {

    if (this.imageFile !== undefined) {
      const id = Math.random().toString(36).substring(2);
      this.ref = this.afStorage.ref('user/' + id);
      this.task = this.ref.put(this.imageFile);

      await this.task.snapshotChanges().pipe(
        finalize(() => {
          this.ref.getDownloadURL().subscribe(url => {
            this.imageUrl = url;
            this.createUserAccount(form.value, this.imageUrl);
          });
        })
      ).subscribe();
    }
  }

  private createUserAccount(value, imageUrl) {
    const username = value.email.split('@')[0];
    console.log('image', imageUrl);
    const data = {
      ...value,
      username,
      imageUrl
    };

    this.profileService.createUserProfile(data).subscribe(
      res => {
        if (res.responseCode === 1000) {
          localStorage.setItem(environment.glingler_token_key, res.token);
          this.router.navigate(['/' + res.router]);
        }
      },
      err => {
        console.log(err);
      }
    );
  }


  fileInputChangeEvent(event: Event) {
    this.profileImgHide = false;
    const target: any = event.target;
    const file: File = target.files[0];
    this.imageFile = file;

    const reader = new FileReader();
    reader.onload = (e) => {
      this.imageUrl = e.target.result;
    };
    reader.readAsDataURL(file);
  }

}
