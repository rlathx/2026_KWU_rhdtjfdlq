import './Badge.css'

export default function Badge({ variant = 'verified' }) {
  const label = variant === 'verified' ? '인증됨' : '미인증'

  return <span className={`badge badge--${variant}`}>{label}</span>
}
