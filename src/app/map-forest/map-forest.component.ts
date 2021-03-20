import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment.prod';
import * as mapboxgl from 'mapbox-gl';

@Component({
  selector: 'app-map-forest',
  templateUrl: './map-forest.component.html',
  styleUrls: ['./map-forest.component.css']
})
export class MapForestComponent implements OnInit {
  lat = 19.201255;
  lng = -99.141044;


  map: mapboxgl.Map;

  constructor() { }

  ngOnInit(): void {

    (mapboxgl as any).accessToken = environment.mapboxKey;

    this.map = new mapboxgl.Map({
      container: 'map-mapbox',
      style: 'mapbox://styles/mapbox/streets-v11', // style URL
      center: [this.lng, this.lat],
      zoom: 16.6
    });
    this.crearMarcador(this.lng, this.lat);


  }

  crearMarcador(lngM: number, latM: number) {
    const marker = new mapboxgl.Marker({
      draggable: true
    })
        .setLngLat([lngM, latM])
        .addTo(this.map);

        marker.on('drag', () => {
            console.log(marker.getLngLat()
            )
        })

  }

}
