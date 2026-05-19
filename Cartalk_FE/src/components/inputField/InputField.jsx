import './InputField.css'
import './InputField.css'

export default function InputField({
  id,
  type = 'text',
  name,
  placeholder,
  autoComplete,
  required,
  status = 'default',
  errorMsg,
  value,
  onChange,
  onKeyDown,
}) {
  return (
    <div className='input-field'>
      <input
        id={id}
        className={`input-field__input input-field__input--${status}`}
        type={type}
        name={name}
        placeholder={placeholder}
        autoComplete={autoComplete}
        required={required}
        value={value}
        onChange={onChange}
        onKeyDown={onKeyDown}
      />
      {status === 'error' && errorMsg && <p className='input-field__error-msg'>{errorMsg}</p>}
    </div>
  )
}
