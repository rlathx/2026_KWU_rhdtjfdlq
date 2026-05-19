import './ChatBubble.css'

export default function ChatBubble({ type = 'other', text, imageUrl }) {
  return (
    <div className={`chat-bubble-row chat-bubble-row--${type}`}>
      {imageUrl ? (
        <div className='chat-bubble chat-bubble--image'>
          <img src={imageUrl} alt='첨부 이미지' />
        </div>
      ) : (
        <div className={`chat-bubble chat-bubble--${type}`}>{text}</div>
      )}
    </div>
  )
}
