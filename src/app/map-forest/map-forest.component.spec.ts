import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapForestComponent } from './map-forest.component';

describe('MapForestComponent', () => {
  let component: MapForestComponent;
  let fixture: ComponentFixture<MapForestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapForestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MapForestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
