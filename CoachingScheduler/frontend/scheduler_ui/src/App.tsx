import './App.css';
import AppBody from './components/AppBody';
import CoachingSchedule from './components/CoachingSchedule';
import Header from './components/Header';

function App() {
  return (
    <div className="App">
      <Header title="Coaching Scheduler App"/>
      <AppBody>
        <CoachingSchedule></CoachingSchedule>
      </AppBody>
    </div>
  );
}

export default App;
