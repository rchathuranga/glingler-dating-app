import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmAlertDialogComponent } from './sm-alert-dialog.component';

describe('SmAlertDialogComponent', () => {
  let component: SmAlertDialogComponent;
  let fixture: ComponentFixture<SmAlertDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmAlertDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmAlertDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
