module.exports = {
  devServer: {
    proxy: {
      '/app-api': {
        target: 'http://localhost:48081/app-api',
        changeOrigin: true,
      },
    },
  },
}
