import Timeslot from "./Timeslot";
interface Person {
    id: number;
    username: string;
    coach: boolean;
    timeslots: Timeslot[];
}


export default Person