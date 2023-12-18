export interface BuglyPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
