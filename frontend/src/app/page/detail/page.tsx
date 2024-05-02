"use client"
import { useSearchParams } from 'next/navigation';
import Map from '../../ui/Map';

export default function Detail() {
    const searchParams = useSearchParams();
    const firstName = searchParams.get("firstName");
    const lastName = searchParams.get("lastName");
    const latitude = searchParams.get("latitude");
    const longitude = searchParams.get("longitude");

    const fullName = `${firstName} ${lastName}`;
    return (
        <main className="bg-white min-h-screen h-full">
            <Map latitude={parseFloat(latitude || "0")} longitude={parseFloat(longitude || "0")}/>
            <div className="p-5">
                <p className="text-lg">Name: {fullName}</p>
            </div>
        </main>
    );
}