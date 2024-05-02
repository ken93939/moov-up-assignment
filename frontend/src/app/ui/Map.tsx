"use client"
import GoogleMapReact from "google-map-react"
import Marker from "./Marker";
interface MapProps {
    latitude: number;
    longitude: number;
}

export default function Map({ latitude, longitude }: MapProps){
    const defaultProps = {
      zoom: 5
    };
    const center = {
        lat: latitude,
        lng: longitude,
    };
  
    return (
      // Important! Always set the container height explicitly
      <div style={{ height: '50vh', width: '100%' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key: process.env.NEXT_PUBLIC_MAP_KEY as string }}
          center={center}
          defaultZoom={defaultProps.zoom}
        >
            <Marker
                lat={latitude}
                lng={longitude}
            />
        </GoogleMapReact>
      </div>
    );
  }