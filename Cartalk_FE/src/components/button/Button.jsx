import './Button.css'

export default function Button({ variant = 'primary', type = 'button', width, onClick, children }) {
  return (
    <button
      className={`btn btn--${variant}`}
      style={width ? { width } : undefined}
      type={type}
      onClick={onClick}
    >
      {children}
    </button>
  )
}
