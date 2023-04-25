import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { TeacherService } from './teacher.service';
import { Observable } from 'rxjs';
import { Teacher } from '../interfaces/teacher.interface';

describe('TeacherService', () => {
  let service: TeacherService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ]
    });
    service = TestBed.inject(TeacherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return a Teacher[] Observable', () => {
    let teachers = service.all(); 
    expect(teachers).toBeInstanceOf(Observable<Teacher[]>);
  });

  it('should return a specific teacher', () => {
    let teachers = service.detail("1"); 
    expect(teachers).toBeInstanceOf(Observable<Teacher>);
  });

  //let teacher: Teacher = {id:4, lastName:"test", firstName: "teacher", createdAt: new Date(), updatedAt: new Date()};

});
