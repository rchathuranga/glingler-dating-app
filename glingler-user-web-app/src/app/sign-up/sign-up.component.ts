import {Component, OnInit} from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import {AngularFireStorage, AngularFireStorageReference, AngularFireUploadTask} from '@angular/fire/storage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  ref: AngularFireStorageReference;
  task: AngularFireUploadTask;

  passwordHide = true;

  constructor(private formBuilder: FormBuilder, private afStorage: AngularFireStorage, private router: Router) {
  }

  ngOnInit(): void {
  }

  btnCreateAccount(form: NgForm) {
    console.log(form.value);
    // this.uploadProfilePicture();

    // callbackend

    if (true) {
      this.router.navigate(['application']);
    }
  }


  uploadProfilePicture(event: Event) {
    /*const id = Math.random().toString(36).substring(2);
    this.ref = this.afStorage.ref('user/' + id);
    this.task = this.ref.put(event.target.files[0]);*/
  }

}
