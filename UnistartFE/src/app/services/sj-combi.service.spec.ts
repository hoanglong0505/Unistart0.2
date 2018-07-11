import { TestBed, inject } from '@angular/core/testing';

import { SjCombiService } from './sj-combi.service';

describe('SjCombiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SjCombiService]
    });
  });

  it('should be created', inject([SjCombiService], (service: SjCombiService) => {
    expect(service).toBeTruthy();
  }));
});
