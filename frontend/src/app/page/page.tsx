"use client"
import { useRouter } from "next/navigation";
import { Person } from "../model/Person";
import Card from "../ui/Card";
import { useEffect, useState } from "react";



export default function Home() {
  const [personList, setPersonList] = useState<Person[]>([]); 
  useEffect(() => {
    const callApi = async () => {
      const path = `${process.env.NEXT_PUBLIC_PERSON_API}/people`;
      const res = await fetch(path);
      const data: Person[] = await res.json();
      setPersonList(data);
    };
    callApi();
  }, []);

  const router = useRouter();
  const handleClick = (person: Person) => {
    const {firstName, lastName, latitude, longitude} = person;
      const queryParams = new URLSearchParams({
        firstName,
        lastName,
        latitude: new Number(latitude).toString(),
        longitude: new Number(longitude).toString(),
      }).toString();
      router.push(`/page/detail?${queryParams}`);
  };

  const items = personList.map(person =>
    <li key={`${person.firstName} ${person.lastName}`}>
      <Card
        person={person}
        onClick={() => handleClick(person)}
      />
    </li>
  );

  return (
    <main className="flex min-h-screen h-full flex-col justify-between p-5 bg-white">
      <ul>{items}</ul>
    </main>
  );
}
