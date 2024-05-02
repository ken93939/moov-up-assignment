import { Person } from "../model/Person";

interface CardProps {
    person: Person,
    onClick: () => void;
}

export default function Card({ person, onClick }: CardProps) {
    const { firstName, lastName, picture } = person;
    const name = `${firstName} ${lastName}`;
    return (
        <div className="flex items-center p-5 bg-slate-100 mb-2 cursor-pointer" onClick={onClick}>
            <img src={picture} width={120} height={120} className="mr-8 max-w-[120px] max-h-[120px] object-cover"/>
            <p className="text-lg">{name}</p>
        </div>
    );
}