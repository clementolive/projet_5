/**
* @jest-environment jsdom
*/

import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { SessionService } from 'src/app/services/session.service';

import { LoginComponent } from './login.component';
import { AuthService } from '../../services/auth.service';
import { RegisterComponent } from '../register/register.component';

import {
  getByTestId, screen
} from '@testing-library/dom'
import userEvent from '@testing-library/user-event'

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [SessionService],
      imports: [
        RouterTestingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule]
    })
      .compileComponents();
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Integration test 
  it('should create and fill login form', async () => {
    expect(component).toBeTruthy();

    expect(screen.findByText("Email")).toBeTruthy();
    expect(screen.findByText("Password")).toBeTruthy();

    const my_email = document.querySelector("input[formControlName='email']")!;
    userEvent.type(my_email, "email@test.com");
    const my_password = document.querySelector("input[formControlName='password']")!;
    userEvent.type(my_password, "password");
    const my_submit = document.querySelectorAll('input[type=submit]');

    await userEvent.click(my_submit[0]);

  });

  

});
