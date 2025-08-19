/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'http://localhost:8081/api/:path*', // Proxy to backend
      },
    ]
  },
}

module.exports = nextConfig
