import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UniArticleComponent } from './uni-article.component';

describe('UniArticleComponent', () => {
  let component: UniArticleComponent;
  let fixture: ComponentFixture<UniArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
