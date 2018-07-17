import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MbtiDetailComponent } from './mbti-detail.component';

describe('MbtiDetailComponent', () => {
  let component: MbtiDetailComponent;
  let fixture: ComponentFixture<MbtiDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MbtiDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MbtiDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
