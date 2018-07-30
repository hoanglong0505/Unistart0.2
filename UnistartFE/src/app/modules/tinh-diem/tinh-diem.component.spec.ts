import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TinhDiemComponent } from './tinh-diem.component';

describe('TinhDiemComponent', () => {
  let component: TinhDiemComponent;
  let fixture: ComponentFixture<TinhDiemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TinhDiemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TinhDiemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
