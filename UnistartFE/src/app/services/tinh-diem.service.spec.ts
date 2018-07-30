import { TestBed, inject } from '@angular/core/testing';

import { TinhDiemService } from './tinh-diem.service';

describe('TinhDiemService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TinhDiemService]
    });
  });

  it('should be created', inject([TinhDiemService], (service: TinhDiemService) => {
    expect(service).toBeTruthy();
  }));
});
