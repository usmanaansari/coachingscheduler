const BASE_URL = 'http://localhost:8091';

const api = {
    getAllStudents: async() => {
        const response = await fetch(`${BASE_URL}/allStudents`)
        return response.json()
    },
    getBookedTimeslotsForStudent: async(username: string) => {
        const response = await fetch(`${BASE_URL}/bookedFutureTimeslotsForStudent?username=${username}`)
        return response.json() 
    },
    getPersonById: async(personId: number) => {
        const response = await fetch(`${BASE_URL}/getPersonById?coachId=${personId}`)
        return response.json()
    },
    getAllTimeslots: async() => {
        const response = await fetch(`${BASE_URL}/allTimeslots`)
        return response.json()
    },
    getAllCoaches: async() => {
        const response = await fetch(`${BASE_URL}/allCoaches`)
        return response.json()
    },
    getUpcomingScheduleForCoach: async(username: string) => {
        const response = await fetch(`${BASE_URL}/allFutureTimeslotsForCoach?username=${username}`)
        return response.json()
    },
    completedTimeslotsForCoach: async(username: string) => {
        const response = await fetch(`${BASE_URL}/completedTimeslotsForCoach?username=${username}`)
        return response.json()
    },
    getAllTimeslotsForCoaches: async() => {
        const response = await fetch(`${BASE_URL}/allTimeslotsForCoaches`)
        return response.json()
    },
    getAllTimeslotsForStudents: async() => {
        const response = await fetch(`${BASE_URL}/allTimeslotsForStudents`)
        return response.json()
    },
    createTimeslotEntry: async(timeslotInfo: any) => {
        const response = await fetch(`${BASE_URL}/createTimeslot`,  {mode: "no-cors", method: "POST", 
        headers: { Accept: "application/json", "Content-type": "application/json"
          },
          body: JSON.stringify(timeslotInfo)
        }).then((response) => response.json())

        return response;
    },
    bookTimeslotEntry: async(timeslotInfo: any) => {
        const response = await fetch(`${BASE_URL}/assignTimeslot`,  {mode: "no-cors", method: "POST", headers: { Accept: "application/json", "Content-type": "application/json; charset=UTF-8"
        }, body: timeslotInfo}).then((response) => response.json())

        return response;

    },
    createUserNotes: async(userNotesInfo: any) => {
        const response = await fetch(`${BASE_URL}/createUserNotes?newUserNotes=${userNotesInfo}`,   {mode: "no-cors", method: "POST", headers: { Accept: "application/json", "Content-type": "application/json; charset=UTF-8"
        }, body: userNotesInfo}).then((response) => response.json())
        return response.json()
    }
};

export default api;