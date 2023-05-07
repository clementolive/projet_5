/**
* @jest-environment jsdom
*/

import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SessionService } from 'src/app/services/session.service';
import { expect } from '@jest/globals';
import { MeComponent } from './me.component';

import {
  getByTestId
} from '@testing-library/dom'

describe('MeComponent', () => {
  let component: MeComponent;
  let fixture: ComponentFixture<MeComponent>;

  const mockSessionService = {
    sessionInformation: {
      admin: true,
      id: 1
    }
  }
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MeComponent],
      imports: [
        MatSnackBarModule,
        HttpClientModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ],
      providers: [{ provide: SessionService, useValue: mockSessionService }],
    })
      .compileComponents();

    fixture = TestBed.createComponent(MeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('should go back with window.history', () => {
    let window_state = window.history.state;
    if(window.history.length > 1){
      component.back();
      expect(window_state).not.toBe(window.history.state);
    }else{
      component.back(); 
      expect(window_state).toBe(window.history.state);
    }
  });

  //Integration tests 
  it('should create, check content, then delete ', () => {
    expect(component).toBeTruthy();

    const my_title = document.getElementsByTagName("h1");
    expect(my_title[0].textContent).toContain("User information");

    component.delete(); 
    expect(component.user).toBeFalsy();
  });



  
});


