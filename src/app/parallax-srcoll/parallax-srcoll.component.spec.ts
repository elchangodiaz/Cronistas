import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParallaxSrcollComponent } from './parallax-srcoll.component';

describe('ParallaxSrcollComponent', () => {
  let component: ParallaxSrcollComponent;
  let fixture: ComponentFixture<ParallaxSrcollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParallaxSrcollComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParallaxSrcollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
