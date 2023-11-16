import { useState } from 'react';
import Modal from 'react-modal';

interface RatingPopupProps {
    isOpen: boolean;
    onRequestClose: () => void;
    onSubmit: (data: { rating: number; comment: string }) => void;
  }

const RatingDialog: React.FC<RatingPopupProps> = ({ isOpen, onRequestClose, onSubmit }) => {
  const [rating, setRating] = useState(1);
  const [comment, setComment] = useState('');

  const handleChange = (key: 'rating' | 'comment', value: number | string) => {
    if (key === 'rating') {
      setRating(value as number);
    } else if (key === 'comment') {
      setComment(value as string);
    }
  };

  const handleSubmit = () => {
    onSubmit({ rating, comment });
    onRequestClose();
  };

  return (
    <Modal style={{
        content: {
          width: '80vw', 
          margin: 'auto',
        },
      }}
      className="booking-dialog"
      overlayClassName="booking-dialog-overlay" 
      isOpen={isOpen} onRequestClose={onRequestClose}>
    <button className="close-button" onClick={onRequestClose}>
        X
    </button>
      <h2>Rate student satisfaction </h2>
      <label>
        <select value={rating} onChange={(e) => handleChange('rating', e.target.value)}>
          {[1, 2, 3, 4, 5].map((value) => (
            <option key={value} value={value}>
              {value}
            </option>
          ))}
        </select>
      </label>
      <br />
      <label>
        Comment:
        <textarea value={comment} onChange={(e) => handleChange('comment', e.target.value)} />
      </label>
      <br />
      <button onClick={handleSubmit}>Submit</button>
    </Modal>
  );
};

export default RatingDialog;