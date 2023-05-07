import { ComponentFixture, TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { NotFoundComponent } from './not-found.component';

import {
  getByTestId, screen
} from '@testing-library/dom'
import userEvent from '@testing-library/user-event'

describe('NotFoundComponent', () => {
  let component: NotFoundComponent;
  let fixture: ComponentFixture<NotFoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotFoundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //Integration test (DOM)
  it('should create and display Page not found !', () => {
    expect(component).toBeTruthy();

    expect(screen.findByText("Page not found !")).toBeTruthy();
  });
});
