import { TestBed } from '@angular/core/testing';

import { BlogServiceService } from './blog-service.service';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

describe('BlogServiceService', () => {
  let service: BlogServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
