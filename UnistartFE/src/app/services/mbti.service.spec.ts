import { TestBed, inject } from '@angular/core/testing';

import { MbtiService } from './mbti.service';

describe('MbtiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MbtiService]
    });
  });

  it('should be created', inject([MbtiService], (service: MbtiService) => {
    expect(service).toBeTruthy();
  }));
});
