import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-sm-alert-dialog',
  templateUrl: './sm-alert-dialog.component.html',
  styleUrls: ['./sm-alert-dialog.component.css']
})
export class SmAlertDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: string) {}

  ngOnInit(): void {
  }

}
