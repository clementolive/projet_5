import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { SessionService } from './session.service';
import { User } from '../interfaces/user.interface';
import { SessionInformation } from '../interfaces/sessionInformation.interface';

describe('SessionService', () => {
  let service: SessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  

  it('should not start as logged ', () => {
    expect(service.isLogged).toBe(false);
  });

  it('login should store a user in service.user and log him in', () => {
    let user:SessionInformation = {token: "token", type: "user", id: 3, username:"name", firstName:"firstName",
    lastName: "LastName", admin: false};
    service.logIn(user);
    expect(service.isLogged).toBe(true);
    expect(service.sessionInformation?.username).toBe("name");
  });

  it('should log out', () => {
    let user:SessionInformation = {token: "token", type: "user", id: 3, username:"name", firstName:"firstName",
    lastName: "LastName", admin: false};
    service.logIn(user);
    service.logOut();
    expect(service.isLogged).toBe(false);
    expect(service.sessionInformation).toBe(undefined);
  });

  
});
