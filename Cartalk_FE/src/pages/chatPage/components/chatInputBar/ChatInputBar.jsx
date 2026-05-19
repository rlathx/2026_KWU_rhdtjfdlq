import { useRef } from 'react'
import './ChatInputBar.css'

export default function ChatInputBar({ value, onChange, onSend, onImageSelect }) {
  const fileInputRef = useRef(null)

  const handleKeyDown = (e) => {

    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault()
      onSend?.()
    }
  }

 
  const handleFileChange = (e) => {
    const file = e.target.files[0]
    if (file) {
      onImageSelect?.(file)
    }
   
    e.target.value = ''
  }

  return (
    <div className='chat-input-bar'>
   
      <input
        type='file'
        accept='image/*'
        ref={fileInputRef}
        style={{ display: 'none' }}
        onChange={handleFileChange}
      />

      <button 
        className='chat-input-bar__plus' 
        onClick={() => fileInputRef.current.click()}
      >
        +
      </button>

      <input
        className='chat-input-bar__input'
        type='text'
        placeholder='메세지 입력'
        value={value}
        onChange={onChange}
        onKeyDown={handleKeyDown}
      />

      <button className='chat-input-bar__send' onClick={onSend}>
        전송
      </button>
    </div>
  )
}