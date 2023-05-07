import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { expect } from '@jest/globals';
import { RegisterComponent } from './register.component';

import {
  getByTestId, screen
} from '@testing-library/dom'
import userEvent from '@testing-library/user-event'

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,  
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Integration test 
  it('should create and fill register form', async () => {
    expect(component).toBeTruthy();

    expect(screen.findByText("Email")).toBeTruthy();
    expect(screen.findByText("Password")).toBeTruthy();
    expect(screen.findByText("First name")).toBeTruthy();
    expect(screen.findByText("Last name")).toBeTruthy();

    const my_email = document.querySelector("input[formControlName='email']")!;
    userEvent.type(my_email, "email@test.com");
    const my_password = document.querySelector("input[formControlName='password']")!;
    userEvent.type(my_password, "password");
    const my_firstName = document.querySelector("input[formControlName='firstName']")!;
    userEvent.type(my_firstName, "firstName");
    const my_lastName = document.querySelector("input[formControlName='lastName']")!;
    userEvent.type(my_lastName, "lastName");

    const my_submit = document.querySelectorAll('input[type=submit]');
    await userEvent.click(my_submit[0]);
  });

});
