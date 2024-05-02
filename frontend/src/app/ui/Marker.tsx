interface MarkerProps {
    lat: number;
    lng: number;
}

export default function Marker(props: MarkerProps) {
    return (
        <div className="rounded-full bg-red-600" style={{width: "25px", height: "25px"}}>
        </div>
    );
}