import './globals.css'

export const metadata = {
  title: 'Blue Zone Monitoring Client',
  description: 'Monitoring dashboard for Blue Zone ordering system',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  )
}
